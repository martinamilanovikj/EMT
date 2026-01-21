import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import {
    Button,
    TextField,
    Container,
    Typography,
    Box,
    Paper
} from '@mui/material';
import axios from 'axios';

const LoginPage = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();

    const handleLogin = async (e) => {
        e.preventDefault();

        try {
            const response = await axios.post('http://localhost:9090/api/user/login', {
                username,
                password
            });

            // Save token to localStorage
            localStorage.setItem('jwtToken', response.data.token);

            // Redirect to home page or dashboard
            navigate('/');
        } catch (error) {
            alert('Login failed: ' + (error.response?.data?.message || error.message));
        }
    };

    return (
        <Container component="main" maxWidth="xs">
            <Paper elevation={3} sx={{ padding: 4, marginTop: 8 }}>
                <Typography component="h1" variant="h5" align="center">
                    Login
                </Typography>
                <Box component="form" onSubmit={handleLogin} sx={{ mt: 2 }}>
                    <TextField
                        margin="normal"
                        required
                        fullWidth
                        label="Username"
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                    />
                    <TextField
                        margin="normal"
                        required
                        fullWidth
                        label="Password"
                        type="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                    />
                    <Button
                        type="submit"
                        fullWidth
                        variant="contained"
                        sx={{ mt: 3 }}
                    >
                        Sign In
                    </Button>
                </Box>
            </Paper>
        </Container>
    );
};

export default LoginPage;
