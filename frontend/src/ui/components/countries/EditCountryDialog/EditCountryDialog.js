import React, { useState, useEffect } from 'react';
import { Dialog, DialogTitle, DialogContent, DialogActions, TextField, Button } from '@mui/material';

const EditCountryDialog = ({ open, onClose, country, onEdit }) => {
    const [name, setName] = useState('');
    const [continent, setContinent] = useState('');

    useEffect(() => {
        if (country) {
            setName(country.name);
            setContinent(country.continent);
        }
    }, [country]);

    const handleSubmit = () => {
        onEdit(country.id, { name, continent });
        onClose();
    };

    return (
        <Dialog open={open} onClose={onClose}>
            <DialogTitle>Edit Country</DialogTitle>
            <DialogContent>
                <TextField
                    label="Name"
                    fullWidth
                    margin="dense"
                    value={name}
                    onChange={(e) => setName(e.target.value)}
                />
                <TextField
                    label="Continent"
                    fullWidth
                    margin="dense"
                    value={continent}
                    onChange={(e) => setContinent(e.target.value)}
                />
            </DialogContent>
            <DialogActions>
                <Button onClick={onClose}>Cancel</Button>
                <Button variant="contained" onClick={handleSubmit}>Save</Button>
            </DialogActions>
        </Dialog>
    );
};

export default EditCountryDialog;
