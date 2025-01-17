import React, { useState, useEffect } from "react";
import { CameraEndpoint } from "Frontend/generated/endpoints";
import { TextField, Grid, GridColumn, Dialog } from "@vaadin/react-components";
import { TextFieldChangeEvent } from "@vaadin/text-field";
import Camera from "Frontend/generated/com/lcaohoanq/samplewebapplication/models/Camera";
import { Button, Typography } from "@mui/material";
import { toast } from "react-toastify";
import { getCookie } from "Frontend/utils/cookiesUtils";
import { useNavigate } from "react-router-dom";

const CameraManagement: React.FC = () => {
    const [cameras, setCameras] = useState<Camera[]>([]);
    const [newCamera, setNewCamera] = useState<Camera>({
        brand: "",
        model: "",
        description: "",
        thumbnail: "",
        price: 0,
    });
    const [editCamera, setEditCamera] = useState<Camera | null>(null);
    const [deleteCamera, setDeleteCamera] = useState<Camera | null>(null);
    const [isDialogOpen, setIsDialogOpen] = useState(false);
    const [isDeleteDialogOpen, setIsDeleteDialogOpen] = useState(false);
    const navigate = useNavigate();
    const [loading, setLoading] = useState(true);

    //check if the user is logged in via get token from cookie
    const token = getCookie("access_token");
    // if (!token) {
    //     navigate('/Login');
    // }

    useEffect(() => {
        const token = getCookie("access_token");

        if (!token) {
            navigate("/Login"); // Redirect if no token
            setLoading(false); // Stop loading state after redirection
            return; // Exit effect
        }

        // Fetch data if the token exists
        fetchCameras()
            .then((response) => {
                // Handle successful response here
            })
            .catch((error) => {
                console.error(error);
                // Optionally handle error
            })
            .finally(() => {
                setLoading(false); // Stop loading state after fetch
            });
    }, [navigate]);

    const fetchCameras = async () => {
        const fetchedCameras = await CameraEndpoint.findAll();
        const validCameras =
            fetchedCameras?.filter(
                (camera): camera is Camera => camera !== undefined,
            ) || [];
        setCameras(validCameras);
    };

    const handleInputChange = (event: TextFieldChangeEvent) => {
        const { name, value } = event.target;
        setNewCamera({ ...newCamera, [name]: value });
    };

    const handleEditInputChange = (event: TextFieldChangeEvent) => {
        if (editCamera) {
            const { name, value } = event.target;
            setEditCamera({ ...editCamera, [name]: value });
        }
    };

    const handleSubmit = async () => {
        const { brand, model, description, thumbnail, price } = newCamera;

        if (!brand || !model || !description || !price) {
            toast.error("Please fill in all fields!");
            console.log("Please fill in all fields!");
            return;
        }

        await CameraEndpoint.save(newCamera);
        setNewCamera({
            brand: "",
            model: "",
            description: "",
            thumbnail: "",
            price: 0,
        });
        await fetchCameras();
    };

    const handleUpdate = async () => {
        if (editCamera) {
            await CameraEndpoint.save(editCamera);
            setEditCamera(null);
            setIsDialogOpen(false);
            await fetchCameras();
        }
    };

    const handleDelete = async () => {
        if (deleteCamera) {
            await CameraEndpoint.delete(deleteCamera.id!);
            setDeleteCamera(null);
            setIsDeleteDialogOpen(false);
            await fetchCameras();
        }
    };

    const handleEdit = (camera: Camera) => {
        setEditCamera(camera);
        setIsDialogOpen(true);
    };

    const handleDeleteClick = (camera: Camera) => {
        setDeleteCamera(camera);
        setIsDeleteDialogOpen(true);
    };

    return (
        <div>
            <h1 className="text-center">Camera Store</h1>
            <div className="flex flex-col justify-center items-center mb-5">
                <TextField
                    label="Brand"
                    name="brand"
                    value={newCamera.brand}
                    onChange={handleInputChange}
                    required={true}
                />
                <TextField
                    label="Model"
                    name="model"
                    value={newCamera.model}
                    onChange={handleInputChange}
                    required={true}
                />
                <TextField
                    label="Description"
                    name="description"
                    value={
                        newCamera.description == null
                            ? "Not Provided"
                            : newCamera.description
                    }
                    onChange={handleInputChange}
                    required={true}
                />
                <TextField
                    label="Thumbnail"
                    name="thumbnail"
                    value={
                        newCamera.thumbnail == null
                            ? "https://www.dpreview.com/files/p/articles/2745050232/images/intro.jpeg"
                            : newCamera.thumbnail
                    }
                    onChange={handleInputChange}
                    required={false}
                />
                <TextField
                    label="Price"
                    name="price"
                    value={newCamera.price?.toString()}
                    onChange={handleInputChange}
                    required={true}
                />
                <Button variant="contained" onClick={handleSubmit}>
                    Add Camera
                </Button>
            </div>

            <Typography variant={"h4"}>Camera List</Typography>

            <Grid className="p-4" items={cameras}>
                <GridColumn path="brand" header="Brand" />
                <GridColumn path="model" header="Model" />
                <GridColumn path="description" header="Description" />
                <GridColumn
                    path="thumbnail"
                    header="Thumbnail"
                    renderer={({ item }: { item: Camera }) => (
                        <img
                            src={item.thumbnail}
                            alt={item.model}
                            style={{ width: "100px" }}
                        />
                    )}
                />
                <GridColumn path="price" header="Price" />
                <GridColumn
                    header="Actions"
                    renderer={({ item }: { item: Camera }) => (
                        <div className="flex flex-row gap-3">
                            <Button
                                variant="contained"
                                onClick={() => handleEdit(item)}
                            >
                                Edit
                            </Button>
                            <Button
                                variant="contained"
                                color="error"
                                onClick={() => handleDeleteClick(item)}
                            >
                                Delete
                            </Button>
                        </div>
                    )}
                />
            </Grid>
            {editCamera && (
                <Dialog
                    opened={isDialogOpen}
                    onOpenedChanged={({ detail }) =>
                        setIsDialogOpen(detail.value)
                    }
                >
                    <h2>Edit Camera</h2>
                    <div className="flex flex-col justify-center items-center mb-5">
                        <TextField
                            label="Brand"
                            name="brand"
                            value={editCamera.brand}
                            onChange={handleEditInputChange}
                        />
                        <TextField
                            label="Model"
                            name="model"
                            value={editCamera.model}
                            onChange={handleEditInputChange}
                        />
                        <TextField
                            label="Price"
                            name="price"
                            value={editCamera.price?.toString()}
                            onChange={handleEditInputChange}
                        />
                        <TextField
                            label="Description"
                            name="description"
                            value={editCamera.description}
                            onChange={handleEditInputChange}
                        />
                        <TextField
                            label="Thumbnail"
                            name="thumbnail"
                            value={editCamera.thumbnail}
                            onChange={handleEditInputChange}
                        />
                        <Button
                            variant="contained"
                            color="success"
                            onClick={handleUpdate}
                        >
                            Submit
                        </Button>
                    </div>
                </Dialog>
            )}
            {deleteCamera && (
                <Dialog
                    opened={isDeleteDialogOpen}
                    onOpenedChanged={({ detail }) =>
                        setIsDeleteDialogOpen(detail.value)
                    }
                >
                    <h2>Confirm Delete</h2>
                    <p>
                        Are you sure you want to delete the camera{" "}
                        {deleteCamera.brand} {deleteCamera.model}?
                    </p>
                    <Button variant="contained" onClick={handleDelete}>
                        Yes
                    </Button>
                    <Button
                        variant="contained"
                        onClick={() => setIsDeleteDialogOpen(false)}
                    >
                        No
                    </Button>
                </Dialog>
            )}
        </div>
    );
};

export default CameraManagement;
