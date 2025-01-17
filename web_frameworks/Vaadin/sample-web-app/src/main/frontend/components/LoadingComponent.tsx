import { Container } from "@mui/material";
import CircularProgress from "@mui/material/CircularProgress";
import React from "react";

export const LoadingComponent: React.FC = () => {
    return (
        <Container
            sx={{
                display: "flex",
                justifyContent: "center",
                alignItems: "center",
                height: "100vh",
            }}
        >
            <CircularProgress />
        </Container>
    );
};
