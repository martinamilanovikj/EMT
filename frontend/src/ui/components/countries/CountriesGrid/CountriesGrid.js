import React from 'react';
import {  IconButton, Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow } from '@mui/material';
import { Edit, Delete } from '@mui/icons-material';

const CountriesGrid = ({ countries, onEdit, onDelete }) => {
    return (
        <TableContainer component={Paper}>
            <Table>
                <TableHead>
                    <TableRow>
                        <TableCell>Name</TableCell>
                        <TableCell>Continent</TableCell>
                        <TableCell align="right">Actions</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {countries.map((country) => (
                        <TableRow key={country.id}>
                            <TableCell>{country.name}</TableCell>
                            <TableCell>{country.continent}</TableCell>
                            <TableCell align="right">
                                <IconButton onClick={() => onEdit(country)}><Edit /></IconButton>
                                <IconButton onClick={() => onDelete(country.id)}><Delete /></IconButton>
                            </TableCell>
                        </TableRow>
                    ))}
                </TableBody>
            </Table>
        </TableContainer>
    );
};

export default CountriesGrid;
