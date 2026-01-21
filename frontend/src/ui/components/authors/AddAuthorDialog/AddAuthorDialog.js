import React, { useState, useEffect } from "react";
import { Dialog, DialogTitle, DialogContent, DialogActions, Button, TextField, MenuItem, Select, InputLabel, FormControl } from "@mui/material";
import axios from 'axios';

const AddAuthorDialog = ({ open, onClose, onAdd }) => {
    const [name, setName] = useState('');
    const [surname, setSurname] = useState('');
    const [countryId, setCountryId] = useState('');
    const [countries, setCountries] = useState([]);

    useEffect(() => {
        if (open) {
            axios.get('http://localhost:9090/api/country')  // Endpoint for countries
                .then((response) => {
                    setCountries(response.data);
                })
                .catch((error) => console.error("Error fetching countries:", error));
        }
    }, [open]);

    const handleSubmit = () => {
        const newAuthor = { name, surname, countryId };
        onAdd(newAuthor);
        setName('');
        setSurname('');
        setCountryId('');
        onClose();
    };

    return (
        <Dialog open={open} onClose={onClose}>
            <DialogTitle>Add Author</DialogTitle>
            <DialogContent>
                <TextField
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
                <Button onClick={handleSubmit} variant="contained" color="primary">
                    Add Author
                </Button>
            </DialogActions>
        </Dialog>
    );
};

export default AddAuthorDialog;