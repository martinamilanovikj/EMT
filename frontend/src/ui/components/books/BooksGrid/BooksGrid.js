import React from "react";
import {
    Grid,
    Card,
    CardContent,
    Typography,
    CardActions,
    Button,
} from "@mui/material";

const BooksGrid = ({ books, onEdit, onDelete }) => {
    return (
        <Grid container spacing={2} padding={2}>
            {books.map((book) => (
                <Grid item xs={12} sm={6} md={4} key={book.id}>
                    <Card>
                        <CardContent>
                            <Typography variant="h6">{book.name}</Typography>
                            <Typography color="textSecondary">
                                Category: {book.category}
                            </Typography>
                            <Typography color="textSecondary">
                                Author ID: {book.authorId}
                            </Typography>
                            <Typography color="textSecondary">
                                Available: {book.availableCopies}
                            </Typography>
                            <Typography color="textSecondary">
                                Date: {new Date(book.date).toLocaleDateString()}
                            </Typography>
                        </CardContent>
                        <CardActions>
                            <Button
                                variant="outlined"
                                color="primary"
                                size="small"
                                onClick={() => onEdit(book)}
                            >
                                Edit
                            </Button>
                            <Button
                                variant="outlined"
                                color="secondary"
                                size="small"
                                onClick={() => onDelete(book.id)}
                            >
                                Delete
                            </Button>
                        </CardActions>
                    </Card>
                </Grid>
            ))}
        </Grid>
    );
};

export default BooksGrid;
