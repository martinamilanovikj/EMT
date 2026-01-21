import React, { useState, useEffect } from 'react';
import {
    Dialog, DialogTitle, DialogContent, DialogActions, Button, TextField, MenuItem, FormControl, InputLabel, Select} from '@mui/material';
import axios from 'axios';
const EditAuthorDialog = ({ open, onClose, author, onEdit }) => {
    const [name, setName] = useState('');
    const [surname, setSurname] = useState('');
    const [countryId, setCountryId] = useState('');
    const [countries, setCountries] = useState([]);
    const [loading, setLoading] = useState(false);

    useEffect(() => {
        if (open) {
            setLoading(true);
            axios.get('http://localhost:9090/api/country')
                .then((response) => {
                    setCountries(response.data);
                })
                .catch((error) => console.error("Error fetching countries:", error))
                .finally(() => setLoading(false));
        }
    }, [open]);

    useEffect(() => {
        if (author) {
            setName(author.name || '');
            setSurname(author.surname || '');
            setCountryId(author.countryId || '');
        }
    }, [author]);

    const handleSubmit = () => {
        if (name.trim() && surname.trim() && countryId) {
            onEdit(author.id, { name, surname, countryId });
            onClose();
        }
    };

    return (
        <Dialog open={open} onClose={onClose}>
            <DialogTitle>Edit Author</DialogTitle>
            <DialogContent>
                <TextField
                    autoFocus
                    margin="dense"
                    label="Name"
                    fullWidth
                    value={name}
                    onChange={(e) => setName(e.target.value)}
                />
                <TextField
                    margin="dense"
                    label="Surname"
                    fullWidth
                    value={surname}
                    onChange={(e) => setSurname(e.target.value)}
                />
                <FormControl fullWidth margin="dense">
                    <InputLabel>Country</InputLabel>
                    <Select
                        value={countryId}
                        onChange={(e) => setCountryId(e.target.value)}
                        label="Country"
                        disabled={loading}
                    >
                        {countries.map((country) => (
                            <MenuItem key={country.id} value={country.id}>
                                {country.name}
                            </MenuItem>
                        ))}
                    </Select>
                </FormControl>
            </DialogContent>
            <DialogActions>
                <Button onClick={onClose}>Cancel</Button>
                <Button onClick={handleSubmit} variant="contained" color="primary" disabled={loading}>
                    {loading ? 'Saving...' : 'Save'}
                </Button>
            </DialogActions>
        </Dialog>
    );
};

export default EditAuthorDialog;
