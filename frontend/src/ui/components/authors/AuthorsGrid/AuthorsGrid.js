import React from 'react';
import { Grid, IconButton, Paper, Typography } from '@mui/material';
import { Edit, Delete } from '@mui/icons-material';

const AuthorsGrid = ({ authors, onEdit, onDelete }) => (
    <Grid container spacing={2}>
        {authors.map((author) => (
            <Grid item xs={12} md={6} lg={4} key={author.id}>
                <Paper sx={{ p: 2 }}>
                    <Typography variant="h6">{author.name}</Typography>
                    <IconButton onClick={() => onEdit(author)}><Edit /></IconButton>
                    <IconButton onClick={() => onDelete(author.id)}><Delete /></IconButton>
                </Paper>
            </Grid>
        ))}
    </Grid>
);

export default AuthorsGrid;
