import React, { useState, useEffect } from 'react';
import {Dialog, DialogTitle, DialogContent, DialogActions, Button, TextField, MenuItem} from '@mui/material';

const categories = ['NOVEL', 'THRILLER', 'HISTORY', 'FANTASY', 'BIOGRAPHY'];

const EditBookDialog = ({ open, onClose, book, onEdit }) => {
    const [form, setForm] = useState({
        name: '',
        category: '',
        authorId: '',
        availableCopies: ''
    });

    useEffect(() => {
        if (book) {
            setForm({
                name: book.name,
                category: book.category,
                authorId: book.authorId,
                availableCopies: book.availableCopies
            });
        }
    }, [book]);

    const handleChange = (e) => {
        setForm({ ...form, [e.target.name]: e.target.value });
    };

    const handleSubmit = () => {
        const updatedBook = {
            ...form,
            authorId: parseInt(form.authorId),
            availableCopies: parseInt(form.availableCopies)
        };
        onEdit(book.id, updatedBook);
        onClose();
    };

    return (
        <Dialog open={open} onClose={onClose}>
            <DialogTitle>Edit Book</DialogTitle>
            <DialogContent>
                <TextField name="name" label="Name" fullWidth margin="dense" value={form.name} onChange={handleChange} />
                <TextField name="category" select label="Category" fullWidth margin="dense" value={form.category} onChange={handleChange}>
                    {categories.map(c => <MenuItem key={c} value={c}>{c}</MenuItem>)}
                </TextField>
                <TextField name="authorId" label="Author ID" type="number" fullWidth margin="dense" value={form.authorId} onChange={handleChange} />
                <TextField name="availableCopies" label="Available Copies" type="number" fullWidth margin="dense" value={form.availableCopies} onChange={handleChange} />
            </DialogContent>
            <DialogActions>
                <Button onClick={onClose}>Cancel</Button>
                <Button onClick={handleSubmit} variant="contained">Save</Button>
            </DialogActions>
        </Dialog>
    );
};

export default EditBookDialog;
