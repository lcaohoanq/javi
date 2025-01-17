import { RouterConfigurationBuilder } from "@vaadin/hilla-file-router/runtime.js";
import Flow from "Frontend/generated/flow/Flow";
import CameraDetail from "./views/CameraDetail";
import MainLayout from "Frontend/views/@layout";
import LoginView from "Frontend/views/Login";
import RegisterView from "Frontend/views/Register";
import HomeView from "Frontend/views/Home";
import CameraManagement from "Frontend/views/CameraManagement"; // Import CameraDetail view

export const { router, routes } = new RouterConfigurationBuilder()
    .withReactRoutes([
        {
            element: <MainLayout />,
            handle: { title: "Main" },
            children: [
                {
                    path: "/Home",
                    element: <HomeView />,
                    handle: { title: "Home" },
                },
                {
                    path: "/cameras/:id",
                    element: <CameraDetail />,
                    handle: { title: "Camera Detail" },
                },
                {
                    path: "/login",
                    element: <LoginView />,
                    handle: { title: "Login" },
                },
                {
                    path: "/register",
                    element: <RegisterView />,
                    handle: { title: "Register" },
                },
                {
                    path: "/cameras/manage",
                    element: <CameraManagement />,
                    handle: { title: "Camera Management" },
                },
                // Add other routes as needed
            ],
        },
    ])
    .withFallback(Flow)
    .protect()
    .build();
