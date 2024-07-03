package com.example.doctor_clinics.repositories.implementations;

import com.example.doctor_clinics.dtos.education.EducationRequestForm;
import com.example.doctor_clinics.entities.education.Education;
import com.example.doctor_clinics.repositories.EducationRepository;
import com.example.doctor_clinics.row_mappers.EducationRowMapper;
import lombok.RequiredArgsConstructor;
import org.hibernate.dialect.OracleTypes;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class EducationRepositoryImpl implements EducationRepository<Education> {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<Education> addEducationHistoryByDocId(Long docId, EducationRequestForm form) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate.getJdbcTemplate())
            .withProcedureName("add_education_by_doctor_id")
            .declareParameters(getParametersToAddEducation())
            .returningResultSet("out_education_list", new EducationRowMapper());

        try {
            Map<String, Object> result = jdbcCall.execute(getSqlParameterSourceToAddEducation(docId, form));
            return  (List<Education>) result.get("out_education_list");
        } catch (DuplicateKeyException exception) {
            throw new RuntimeException("Educational background already added");
        } catch (DataIntegrityViolationException exception) {
            throw new RuntimeException("Doctor with id=" + docId + " not found");
        } catch (Exception exception) {
            throw new RuntimeException("An error occurred while adding educational background!");
        }
    }

    @Override
    public List<Education> getEducationalHistoriesByDocId(Long docId) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate.getJdbcTemplate())
            .withProcedureName("get_educational_background_by_doctor_id")
            .declareParameters(
                new SqlParameter("in_doc_id", Types.NUMERIC),
                new SqlOutParameter("out_education_list", OracleTypes.CURSOR)
            )
            .returningResultSet("out_education_list", new EducationRowMapper());

        try {
            SqlParameterSource parameterSource = new MapSqlParameterSource().addValue("in_doc_id", docId);
            Map<String, Object> result = jdbcCall.execute(parameterSource);
            return  (List<Education>) result.get("out_education_list");
        } catch (DataIntegrityViolationException exception) {
            throw new RuntimeException("Doctor with id=" + docId + " not found");
        } catch (Exception exception) {
            throw new RuntimeException("An error occurred while adding educational background!");
        }
    }

    private SqlParameter[] getParametersToAddEducation(){
        return new SqlParameter[]{
            new SqlParameter("in_doc_id", Types.NUMERIC),
            new SqlParameter("in_institution_name", Types.VARCHAR),
            new SqlParameter("in_field", Types.VARCHAR),
            new SqlParameter("in_degree", Types.VARCHAR),
            new SqlParameter("in_start_date", Types.DATE),
            new SqlParameter("in_end_date", Types.TIMESTAMP),
            new SqlOutParameter("out_education_list", OracleTypes.CURSOR)
        };
    }

    private SqlParameterSource getSqlParameterSourceToAddEducation(Long docId, EducationRequestForm form) {
        return new MapSqlParameterSource(
            Map.of(
                "in_doc_id", docId,
                "in_institution_name", form.getInstitutionName(),
                "in_field", form.getField(),
                "in_degree", form.getDegree(),
                "in_start_date", form.getStartDate(),
                "in_end_date", form.getEndDate()
            )
        );
    }
}
