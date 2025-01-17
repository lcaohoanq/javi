import { getCookie } from "Frontend/utils/cookiesUtils";
import React from "react";
import { Navigate, Outlet } from "react-router-dom";

interface ProtectedRouteProps {
    redirectPath?: string;
}

const ProtectedRoute: React.FC<ProtectedRouteProps> = ({
    redirectPath = "/login",
}) => {
    const token = getCookie("access_token");

    if (!token) {
        return <Navigate to={redirectPath} replace />;
    }

    return <Outlet />;
};

export default ProtectedRoute;
