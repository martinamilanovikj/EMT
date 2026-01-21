import React, { useState, useEffect } from 'react';
import { Box, Button, CircularProgress, Typography } from '@mui/material';
import axios from 'axios';
import useAuthors from '../../../hooks/useAuthors.js';
import AuthorsGrid from '../../components/authors/AuthorsGrid/AuthorsGrid.js';
import EditAuthorDialog from '../../components/authors/EditAuthorDialog/EditAuthorDialog.js';
import AddAuthorDialog  from '../../components/authors/AddAuthorDialog/AddAuthorDialog.js';

const AuthorsPage = () => {
    const { authors, loading, onAdd, onEdit, onDelete } = useAuthors();
    const [addDialogOpen, setAddDialogOpen] = useState(false);
    const [editDialogOpen, setEditDialogOpen] = useState(false);
    const [selectedAuthor, setSelectedAuthor] = useState(null);
    const [countries, setCountries] = useState([]);

    useEffect(() => {
        axios.get('/api/country')
            .then((res) => setCountries(res.data))
            .catch((err) => console.error("Failed to load countries", err));
    }, []);

    return (
        <Box>
            <Typography variant="h4" gutterBottom>Authors</Typography>
            {loading ? (
                <CircularProgress />
            ) : (
                <>
                    <Box sx={{ display: 'flex', justifyContent: 'flex-end', mb: 2 }}>
                        <Button variant="contained" onClick={() => setAddDialogOpen(true)}>
                            Add Author
                        </Button>
                    </Box>
                    <AuthorsGrid
                        authors={authors}
                        onEdit={(author) => {
                            setSelectedAuthor(author);
                            setEditDialogOpen(true);
                        }}
                        onDelete={onDelete}
                    />
                </>
            )}

            <AddAuthorDialog open={addDialogOpen} onClose={() => setAddDialogOpen(false)} onAdd={onAdd} />
            {selectedAuthor && (
                <EditAuthorDialog
                    open={editDialogOpen}
                    onClose={() => setEditDialogOpen(false)}
                    author={selectedAuthor}
                    onEdit={onEdit}
                    countries={countries}
                />
            )}
        </Box>
    );
};


export default AuthorsPage;


