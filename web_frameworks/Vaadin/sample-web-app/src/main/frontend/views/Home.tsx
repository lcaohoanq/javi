import { ViewConfig } from "@vaadin/hilla-file-router/types.js";
import React, { useEffect, useState } from "react";
import Camera from "Frontend/generated/com/lcaohoanq/samplewebapplication/models/Camera";
import { CameraEndpoint } from "Frontend/generated/endpoints";
import {
    Card,
    CardContent,
    CardActions,
    Button,
    Typography,
    Grid,
    Grid2,
} from "@mui/material";
import { Link } from "react-router-dom";
import { formatCurrency } from "Frontend/utils/currencyUtils";
import CartView from "./CartView";
import { CameraCard } from "Frontend/components/CameraCart";

export const config: ViewConfig = {
    menu: { order: 3, icon: "line-awesome/svg/file.svg" },
    title: "Home",
};

const HomeView: React.FC = () => {
    const [cameras, setCameras] = useState<Camera[]>([]);

    useEffect(() => {
        fetchCameras()
            .then((r) => {})
            .catch((e) => console.error(e));
    }, []);

    const fetchCameras = async () => {
        const fetchedCameras = await CameraEndpoint.findAll();
        const validCameras =
            fetchedCameras?.filter(
                (camera): camera is Camera => camera !== undefined,
            ) || [];
        setCameras(validCameras);
    };

    return (
        <div className="container mx-auto px-4 py-8">
            <Typography variant="h4" component="h1" gutterBottom align="center">
                Welcome to our Camera Store
            </Typography>
            <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-5 gap-4">
                {cameras.map((camera) => (
                    <div key={camera.id} className="flex justify-center">
                        <CameraCard camera={camera} />
                    </div>
                ))}
            </div>
        </div>
    );
};

export default HomeView;
