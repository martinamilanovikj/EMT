
import React, { useState, useEffect } from 'react';
import { Box, Button, CircularProgress, Typography } from '@mui/material';
import useBooks from '../../../hooks/useBooks.js';
import BooksGrid from '../../components/books/BooksGrid/BooksGrid.js';
import EditBookDialog from '../../components/books/EditBookDialog/EditBookDialog.js';
import AddBookDialog  from '../../components/books/AddBookDialog/AddBookDialog.js';

const BooksPage = () => {
    const { books, loading, onAdd, onEdit, onDelete } = useBooks();
    const [addBookDialogOpen, setAddBookDialogOpen] = useState(false);
    const [editBookDialogOpen, setEditBookDialogOpen] = useState(false);
    const [selectedBook, setSelectedBook] = useState(null);

    return (
        <>
            <Box className="books-box">
                {loading && (
                    <Box className="progress-box">
                        <CircularProgress />
                    </Box>
                )}
                {!loading && (
                    <>
                        <Box sx={{ display: 'flex', justifyContent: 'flex-end', mb: 2 }}>
                            <Button variant="contained" color="primary" onClick={() => setAddBookDialogOpen(true)}>
                                Add Book
                            </Button>
                        </Box>
                        <BooksGrid
                            books={books}
                            onEdit={(book) => { setSelectedBook(book); setEditBookDialogOpen(true); }}
                            onDelete={onDelete}
                        />
                    </>
                )}
            </Box>
            <AddBookDialog
                open={addBookDialogOpen}
                onClose={() => setAddBookDialogOpen(false)}
                onAdd={onAdd}
            />
            {selectedBook && (
                <EditBookDialog
                    open={editBookDialogOpen}
                    onClose={() => setEditBookDialogOpen(false)}
                    book={selectedBook}
                    onEdit={onEdit}
                />
            )}
        </>
    );
};

export default BooksPage;


