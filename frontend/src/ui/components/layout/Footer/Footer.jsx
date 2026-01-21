import React from "react";
import { Box, Typography } from "@mui/material";

const Footer = () => {
    return (
        <Box
            sx={{
                position: "fixed",
                bottom: 0,
                left: 0,
                width: "100%",
                backgroundColor: "#1976d2",
                color: "white",
                padding: "10px 0",
                textAlign: "center",
            }}
        >
            <Typography variant="body2">
                © {new Date().getFullYear()} All rights are reserved.
            </Typography>
        </Box>
    );
};

export default Footer;
