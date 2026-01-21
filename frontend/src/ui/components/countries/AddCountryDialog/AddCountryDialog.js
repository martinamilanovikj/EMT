import React, { useState } from 'react';
import { Dialog, DialogTitle, DialogContent, DialogActions, TextField, Button } from '@mui/material';

const AddCountryDialog = ({ open, onClose, onAdd }) => {
    const [name, setName] = useState('');
    const [continent, setContinent] = useState('');

    const handleSubmit = () => {
        onAdd({ name, continent });
        onClose();
        setName('');
        setContinent('');
    };

    return (
        <Dialog open={open} onClose={onClose}>
            <DialogTitle>Add Country</DialogTitle>
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
                <Button variant="contained" onClick={handleSubmit}>Add</Button>
            </DialogActions>
        </Dialog>
    );
};

export default AddCountryDialog;
