package com.example.doctor_clinics.repositories.implementations;

import com.example.doctor_clinics.entities.Doctor;
import com.example.doctor_clinics.repositories.DoctorRepository;
import com.example.doctor_clinics.row_mappers.DoctorRowMapper;
import lombok.RequiredArgsConstructor;
import org.hibernate.dialect.OracleTypes;
import org.jetbrains.annotations.NotNull;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.BadSqlGrammarException;
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
public class DoctorRepositoryImpl implements DoctorRepository<Doctor> {
    private final NamedParameterJdbcTemplate jdbcTemplate;


    @Override
    public Doctor create(Doctor doctor) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate.getJdbcTemplate())
            .withProcedureName("create_doctor_profile")
            .declareParameters(getParametersToCreate())
            .returningResultSet("out_response", new DoctorRowMapper());

        try {
            Map<String, Object> result = jdbcCall.execute(getSqlParameterSourceToCreate(doctor));
            List<Doctor> doctors = (List<Doctor>) result.get("out_response");
            return doctors.isEmpty() ? null : doctors.get(0);
        } catch (BadSqlGrammarException exception) {
            throw new RuntimeException("Doctor with pinfl=" + doctor.getPinfl() + " already exists");
        } catch (Exception exception) {
            throw new RuntimeException("An error occurred while creating profile!");
        }
    }

    @Override
    public Doctor get(Long id) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate.getJdbcTemplate())
            .withProcedureName("get_doc_by_id")
            .declareParameters(
                new SqlParameter("doc_id", Types.NUMERIC),
                new SqlOutParameter("response", OracleTypes.CURSOR)
            )
            .returningResultSet("response", new DoctorRowMapper());

        try {
            SqlParameterSource parameterSource = new MapSqlParameterSource().addValue("doc_id", id);
            Map<String, Object> result = jdbcCall.execute(parameterSource);
            List<Doctor> doctors = (List<Doctor>) result.get("response");
            return doctors.isEmpty() ? null : doctors.get(0);
        } catch (EmptyResultDataAccessException exception) {
            throw new RuntimeException("Doctor with id=" + id + " not fount");
        } catch (Exception exception) {
            throw new RuntimeException("An error occurred while getting profile!");
        }
    }

    @Override
    public Doctor update(Doctor data) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate.getJdbcTemplate())
            .withProcedureName("update_doctor_by_id")
            .declareParameters(getParametersToUpdate())
            .returningResultSet("out_response", new DoctorRowMapper());

        try {
            Map<String, Object> result = jdbcCall.execute(getSqlParameterSourceToUpdate(data));
            List<Doctor> doctors = (List<Doctor>) result.get("out_response");
            return doctors.isEmpty() ? null : doctors.get(0);
        } catch (DataIntegrityViolationException exception) {
            throw new RuntimeException("Doctor with id=" + data.getId() + " not fount");
        } catch (Exception exception) {
            throw new RuntimeException("An error occurred while updating profile!");
        }
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }

    private SqlParameter[] getParametersToCreate(){
        return new SqlParameter[]{
            new SqlParameter("in_pinfl", Types.VARCHAR),
            new SqlParameter("in_first_name", Types.VARCHAR),
            new SqlParameter("in_last_name", Types.VARCHAR),
            new SqlParameter("in_patronymic", Types.VARCHAR),
            new SqlParameter("in_date_of_birth", Types.DATE),
            new SqlParameter("in_picture_url", Types.VARCHAR),
            new SqlParameter("in_phone_number", Types.VARCHAR),
            new SqlParameter("in_email", Types.VARCHAR),
            new SqlParameter("in_telegram", Types.VARCHAR),
            new SqlParameter("in_scientific_degree", Types.VARCHAR),
            new SqlOutParameter("out_response", OracleTypes.CURSOR)
        };
    }

    private SqlParameter[] getParametersToUpdate(){
        return new SqlParameter[]{
            new SqlParameter("in_doc_id", Types.NUMERIC),
            new SqlParameter("in_first_name", Types.VARCHAR),
            new SqlParameter("in_last_name", Types.VARCHAR),
            new SqlParameter("in_patronymic", Types.VARCHAR),
            new SqlParameter("in_phone_number", Types.VARCHAR),
            new SqlParameter("in_email", Types.VARCHAR),
            new SqlParameter("in_telegram", Types.VARCHAR),
            new SqlParameter("in_scientific_degree", Types.VARCHAR),
            new SqlOutParameter("out_response", OracleTypes.CURSOR)
        };
    }

    private SqlParameterSource getSqlParameterSourceToCreate(Doctor doctor) {
        MapSqlParameterSource source = new MapSqlParameterSource(
            Map.of(
                "in_pinfl", doctor.getPinfl(),
                "in_first_name", doctor.getFirstName(),
                "in_last_name", doctor.getLastName(),
                "in_date_of_birth", doctor.getDateOfBirth(),
                "in_picture_url", doctor.getImageUrl(),
                "in_phone_number", doctor.getPhoneNumber()
            )
        );

        return nullCheckParameters(doctor, source);
    }

    private SqlParameterSource getSqlParameterSourceToUpdate(Doctor doctor) {

        MapSqlParameterSource source = new MapSqlParameterSource(
            Map.of(
                "in_doc_id", doctor.getId(),
                "in_first_name", doctor.getFirstName(),
                "in_last_name", doctor.getLastName(),
                "in_phone_number", doctor.getPhoneNumber()
            )
        );

        return nullCheckParameters(doctor, source);
    }

    @NotNull
    private SqlParameterSource nullCheckParameters(Doctor doctor, MapSqlParameterSource source) {
        if (doctor.getPatronymic() != null)
            source.addValue("in_patronymic", doctor.getPatronymic());
        else
            source.addValue("in_patronymic", null);

        if (doctor.getScientificDegree() != null)
            source.addValue("in_scientific_degree", doctor.getScientificDegree());
        else
            source.addValue("in_scientific_degree", null);

        if (doctor.getEmail() != null)
            source.addValue("in_email", doctor.getEmail());
        else
            source.addValue("in_email", null);

        if (doctor.getTelegram() != null)
            source.addValue("in_telegram", doctor.getTelegram());
        else
            source.addValue("in_telegram", null);

        return source;
    }
}
