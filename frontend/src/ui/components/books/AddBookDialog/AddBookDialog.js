import React, { useState } from 'react';
import { Dialog, DialogTitle, DialogContent, DialogActions, Button, TextField, MenuItem} from '@mui/material';

const categories = ['NOVEL', 'THRILLER', 'HISTORY', 'FANTASY', 'BIOGRAPHY'];

const AddBookDialog = ({ open, onClose, onAdd }) => {
    const [form, setForm] = useState({
        name: '',
        category: '',
        authorId: '',
        availableCopies: '',
        date: ''
    });

    const handleChange = (e) => {
        setForm({ ...form, [e.target.name]: e.target.value });
    };

    const handleSubmit = () => {
        const book = {
            ...form,
            authorId: parseInt(form.authorId),
            availableCopies: parseInt(form.availableCopies),
            date: new Date(form.date).toISOString()
        };
        onAdd(book);
        onClose();
        setForm({ name: '', category: '', authorId: '', availableCopies: '', date: '' });
    };

    return (
        <Dialog open={open} onClose={onClose}>
            <DialogTitle>Add Book</DialogTitle>
            <DialogContent>
                <TextField name="name" label="Name" fullWidth margin="dense" value={form.name} onChange={handleChange} />
                <TextField name="category" select label="Category" fullWidth margin="dense" value={form.category} onChange={handleChange}>
                    {categories.map(c => <MenuItem key={c} value={c}>{c}</MenuItem>)}
                </TextField>
                <TextField name="authorId" label="Author ID" type="number" fullWidth margin="dense" value={form.authorId} onChange={handleChange} />
                <TextField name="availableCopies" label="Available Copies" type="number" fullWidth margin="dense" value={form.availableCopies} onChange={handleChange} />
                <TextField name="date" label="Date" type="datetime-local" fullWidth margin="dense" value={form.date} onChange={handleChange} InputLabelProps={{ shrink: true }} />
            </DialogContent>
            <DialogActions>
                <Button onClick={onClose}>Cancel</Button>
                <Button onClick={handleSubmit} variant="contained">Add</Button>
            </DialogActions>
        </Dialog>
    );
};

export default AddBookDialog;
