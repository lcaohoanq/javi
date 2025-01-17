import { useState } from "react";
import { Controller, useForm } from "react-hook-form";
import { yupResolver } from "@hookform/resolvers/yup";
import * as yup from "yup";
import { ViewConfig } from "@vaadin/hilla-file-router/types.js";
import { register as registerService } from "Frontend/generated/AuthEndPoint.js";
import { Alert, Box, Button, TextField, Typography } from "@mui/material";
import { toast, ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { useNavigate } from "react-router-dom";

export const config: ViewConfig = {
    menu: { order: 2, icon: "line-awesome/svg/file.svg" },
    title: "Register",
};

// Define the validation schema using Yup
const schema = yup.object().shape({
    username: yup.string().required("Username is required"),
    email: yup
        .string()
        .email("Invalid email format")
        .required("Email is required"),
    password: yup
        .string()
        .required("Password is required")
        .min(6, "Password must be at least 6 characters"),
});

export default function RegisterView() {
    const {
        control,
        handleSubmit,
        formState: { errors },
    } = useForm({
        resolver: yupResolver(schema),
    });
    const [error, setError] = useState<string | null>(null);
    const navigate = useNavigate();

    // Function to handle form submission
    const onSubmit = async (data: {
        username: string;
        email: string;
        password: string;
    }) => {
        setError(null);

        try {
            const user = await registerService({
                user_name: data.username,
                email: data.email,
                password: data.password,
            });

            if (user) {
                toast.success(
                    `Registration successful, welcome ${user.email}!`,
                );
                setTimeout(() => {
                    navigate("/Login");
                }, 2000);
            } else {
                setError("Registration failed. Please try again.");
            }
        } catch (err) {
            setError(
                "An error occurred during registration. Please try again.",
            );
        }
    };

    return (
        <Box
            sx={{
                display: "flex",
                flexDirection: "column",
                alignItems: "center",
                justifyContent: "center",
                height: "100vh",
                p: 2,
            }}
        >
            <Typography variant="h4" gutterBottom>
                Register
            </Typography>

            {/* Display error message */}
            {error && (
                <Alert severity="error" sx={{ mb: 2 }}>
                    {error}
                </Alert>
            )}

            <Box
                component="form"
                onSubmit={handleSubmit(onSubmit)}
                sx={{
                    display: "flex",
                    flexDirection: "column",
                    gap: 2,
                    width: "100%",
                    maxWidth: "400px",
                }}
            >
                {/* Username Field */}
                <Controller
                    name="username"
                    control={control}
                    defaultValue=""
                    render={({ field }) => (
                        <TextField
                            {...field}
                            label="Username"
                            variant="outlined"
                            fullWidth
                            error={!!errors.username}
                            helperText={errors.username?.message}
                        />
                    )}
                />

                {/* Email Field */}
                <Controller
                    name="email"
                    control={control}
                    defaultValue=""
                    render={({ field }) => (
                        <TextField
                            {...field}
                            label="Email"
                            variant="outlined"
                            fullWidth
                            error={!!errors.email}
                            helperText={errors.email?.message}
                        />
                    )}
                />

                {/* Password Field */}
                <Controller
                    name="password"
                    control={control}
                    defaultValue=""
                    render={({ field }) => (
                        <TextField
                            {...field}
                            label="Password"
                            variant="outlined"
                            type="password"
                            fullWidth
                            error={!!errors.password}
                            helperText={errors.password?.message}
                        />
                    )}
                />

                {/* Submit Button */}
                <Button
                    type="submit"
                    variant="contained"
                    color="primary"
                    fullWidth
                >
                    Register
                </Button>
            </Box>

            <ToastContainer />
        </Box>
    );
}
