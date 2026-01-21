import React, { useState, useEffect } from 'react';
import { Box, Button, CircularProgress, Typography } from '@mui/material';
import useCountries from '../../../hooks/useCountries.js';
import CountriesGrid from '../../components/countries/CountriesGrid/CountriesGrid.js';
import EditCountryDialog from '../../components/countries/EditCountryDialog/EditCountryDialog.js';
import AddCountryDialog  from '../../components/countries/AddCountryDialog/AddCountryDialog.js';
const CountriesPage = () => {
    const { countries, loading, onAdd, onEdit, onDelete } = useCountries();
    const [addDialogOpen, setAddDialogOpen] = useState(false);
    const [editDialogOpen, setEditDialogOpen] = useState(false);
    const [selectedCountry, setSelectedCountry] = useState(null);

    return (
        <Box>
            <Typography variant="h4" gutterBottom>Countries</Typography>

            {loading ? (
                <CircularProgress />
            ) : (
                <>
                    <Box sx={{ display: 'flex', justifyContent: 'flex-end', mb: 2 }}>
                        <Button variant="contained" onClick={() => setAddDialogOpen(true)}>
                            Add Country
                        </Button>
                    </Box>
                    <CountriesGrid
                        countries={countries}
                        onEdit={(country) => {
                            setSelectedCountry(country);
                            setEditDialogOpen(true);
                        }}
                        onDelete={onDelete}
                    />
                </>
            )}

            <AddCountryDialog
                open={addDialogOpen}
                onClose={() => setAddDialogOpen(false)}
                onAdd={onAdd}
            />

            {selectedCountry && (
                <EditCountryDialog
                    open={editDialogOpen}
                    onClose={() => setEditDialogOpen(false)}
                    country={selectedCountry}
                    onEdit={onEdit}
                />
            )}
        </Box>
    );
};

export default CountriesPage;