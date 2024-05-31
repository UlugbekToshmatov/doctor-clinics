package com.example.doctor_clinics.row_mappers;

import com.example.doctor_clinics.entities.Doctor;
import org.jetbrains.annotations.NotNull;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DoctorRowMapper implements RowMapper<Doctor>, ResultSetExtractor<Doctor> {
    @Override
    public Doctor mapRow(@NotNull ResultSet resultSet, int rowNum) throws SQLException {
        return getDoctor(resultSet);
    }

    @Override
    public Doctor extractData(@NotNull ResultSet resultSet) throws SQLException, DataAccessException {
        return getDoctor(resultSet);
    }

    private static Doctor getDoctor(ResultSet resultSet) throws SQLException {
        return Doctor.builder()
            .id(resultSet.getLong("doctor_id"))
            .pinfl(resultSet.getString("pinfl"))
            .firstName(resultSet.getString("first_name"))
            .lastName(resultSet.getString("last_name"))
            .patronymic(resultSet.getString("patronymic"))
            .dateOfBirth(resultSet.getDate("date_of_birth"))
            .imageUrl(resultSet.getString("picture_url"))
            .phoneNumber(resultSet.getString("phone_number"))
            .email(resultSet.getString("email"))
            .telegram(resultSet.getString("telegram"))
            .scientificDegree(resultSet.getString("scientific_degree"))
            .createdDate(resultSet.getDate("created_date"))
            .modifiedDate(resultSet.getDate("updated_date"))
            .build();
    }
}
