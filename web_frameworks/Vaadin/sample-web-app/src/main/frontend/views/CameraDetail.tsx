import { useParams } from "react-router-dom";
import React, { useEffect, useState } from "react";
import Camera from "Frontend/generated/com/lcaohoanq/samplewebapplication/models/Camera";
import { CameraEndpoint } from "Frontend/generated/endpoints";
import { Typography, Card, CardContent, Button } from "@mui/material";
import { Link } from "react-router-dom";
import { LoadingComponent } from "Frontend/components/LoadingComponent";

const CameraDetail: React.FC = () => {
    const { id } = useParams<{ id: string }>();
    const [camera, setCamera] = useState<Camera | undefined>(undefined);
    const [loading, setLoading] = useState(false);

    useEffect(() => {
        if (id) {
            fetchCameraDetails(id); // Use `id` as a string here
        }
    }, [id]);

    const fetchCameraDetails = async (cameraId: string) => {
        try {
            // If CameraEndpoint expects a number, convert the string to a number
            const fetchedCamera = await CameraEndpoint.findById(
                parseInt(cameraId, 10),
            );
            setCamera(fetchedCamera);
        } catch (e) {
            console.error("Error fetching camera details:", e);
        }
    };

    if (!camera) {
        return <LoadingComponent />;
    }

    return (
        <>
            <Button
                variant="contained"
                color="primary"
                component={Link}
                sx={{ textDecoration: "none", m: 3 }}
                to="/Home"
            >
                Back to Home
            </Button>
            <Card className="shadow-lg flex gap-5 m-3">
                <CardContent>
                    <Typography variant="h4" component="h1" gutterBottom>
                        {camera.brand} {camera.model}
                    </Typography>
                    <Typography variant="body1" color="textSecondary" paragraph>
                        {camera.description}
                    </Typography>
                    <img
                        src={camera.thumbnail}
                        alt={camera.model}
                        className="w-full h-auto"
                    />
                    <Typography variant="h5" className="text-green-500">
                        ${camera.price}
                    </Typography>
                </CardContent>
                <div>Details</div>
            </Card>
        </>
    );
};

export default CameraDetail;
