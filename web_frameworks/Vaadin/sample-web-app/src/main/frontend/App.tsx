import React from "react";
import { Route, Routes } from "react-router-dom";
import CameraManagement from "./views/CameraManagement";
import "./main.css";
import LoginView from "Frontend/views/Login";
import RegisterView from "Frontend/views/Register";
import HomeView from "Frontend/views/Home";
import { ToastContainer } from "react-toastify";
import ProtectedRoute from "./components/ProtectedRoute";
import Logout from "./views/Logout";
import CameraDetail from "Frontend/views/CameraDetail";
import Footer from "Frontend/commons/Footer";
// Import other views as needed

const App: React.FC = () => {
    return (
        <>
            <Routes>
                <Route path="/Home" element={<HomeView />} />
                {/* Add other routes as needed */}
                <Route path="/login" element={<LoginView />} />
                <Route path="/register" element={<RegisterView />} />
                <Route path="/cameras/:id" element={<CameraDetail />} />
                <Route element={<ProtectedRoute />}>
                    <Route
                        path="/cameras/manage"
                        element={<CameraManagement />}
                    />
                    <Route path="/logout" element={<Logout />} />
                </Route>

                <ToastContainer />
            </Routes>
            <Footer />
        </>
    );
};

export default App;
