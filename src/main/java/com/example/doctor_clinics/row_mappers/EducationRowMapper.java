package com.example.doctor_clinics.row_mappers;

import com.example.doctor_clinics.entities.education.Education;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EducationRowMapper implements RowMapper<Education> {
    @Override
    public Education mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return Education.builder()
            .docId(resultSet.getLong("doctor_id"))
            .eduId(resultSet.getLong("edu_id"))
            .institutionName(resultSet.getString("institution_name"))
            .specId(resultSet.getLong("spec_id"))
            .field(resultSet.getString("field"))
            .degree(resultSet.getString("degree"))
            .startDate(resultSet.getDate("start_date"))
            .endDate(resultSet.getDate("end_date"))
            .build();
    }
}
