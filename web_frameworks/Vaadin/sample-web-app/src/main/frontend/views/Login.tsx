import { useState } from "react";
import { useForm, Controller } from "react-hook-form";
import { yupResolver } from "@hookform/resolvers/yup";
import * as yup from "yup";
import { login as loginService } from "Frontend/generated/AuthEndPoint.js";
import { ViewConfig } from "@vaadin/hilla-file-router/types.js";
import { TextField, Button, Box, Typography, Alert } from "@mui/material";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { useNavigate } from "react-router-dom";
import { setCookie } from "Frontend/utils/cookiesUtils";

export const config: ViewConfig = {
    menu: { order: 1, icon: "line-awesome/svg/file.svg" },
    title: "Login",
};

// Define the validation schema using Yup
const schema = yup.object().shape({
    username: yup.string().required("Username is required"),
    password: yup.string().required("Password is required"),
});

export default function LoginView() {
    const {
        control,
        handleSubmit,
        formState: { errors },
    } = useForm({
        resolver: yupResolver(schema),
    });
    const [error, setError] = useState<string | null>(null);
    const navigate = useNavigate();

    const onSubmit = async (data: { username: string; password: string }) => {
        setError(null);

        try {
            const user = await loginService(data.username, data.password);

            if (user) {
                toast.success(`Welcome, ${user.email}!`);
                setCookie("access_token", "access_token", 1);
                setTimeout(() => {
                    navigate("/Home");
                }, 3000);
            } else {
                toast.error("Login failed. Please check your credentials.");
                // setError('Login failed. Please check your credentials.');
            }
        } catch (err) {
            toast.error("An error occurred during login. Please try again.");
            // setError('An error occurred during login. Please try again.');
        }
    };

    return (
        <div className="flex flex-col justify-center items-center">
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
                    Login
                </Typography>

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
                    <Controller
                        name="username"
                        control={control}
                        defaultValue=""
                        render={({ field }) => (
                            <TextField
                                {...field}
                                label="Email or Phone Number"
                                variant="outlined"
                                fullWidth
                                error={!!errors.username}
                                helperText={errors.username?.message}
                            />
                        )}
                    />

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

                    <Button
                        type="submit"
                        variant="contained"
                        color="primary"
                        fullWidth
                    >
                        Login
                    </Button>
                </Box>

                <ToastContainer />
            </Box>
        </div>
    );
}
