import React from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Layout from './ui/components/layout/Layout/Layout.js';
import HomePage from './ui/pages/HomePage/HomePage.js';
import BooksPage from './ui/pages/BooksPage/BooksPage.js';
import AuthorsPage from './ui/pages/AuthorsPage/AuthorsPage.js';
import CountriesPage from './ui/pages/CountriesPage/CountriesPage.js';
import LoginPage from "./ui/pages/LoginPage/LoginPage.jsx";

function App() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Layout />}>
                    <Route index element={<HomePage />} />
                    <Route path="books" element={<BooksPage />} />
                    <Route path="authors" element={<AuthorsPage />} />
                    <Route path="countries" element={<CountriesPage />} />
                    <Route path="login" element={<LoginPage />} />
                </Route>
            </Routes>
        </BrowserRouter>
    );
}

export default App;
