import { Typography } from "@mui/material";
import { ViewConfig } from "@vaadin/hilla-file-router/types.js";
import { eraseCookie, getCookie } from "Frontend/utils/cookiesUtils";
import { useNavigate } from "react-router-dom";
import { useEffect } from "react";

export const config: ViewConfig = {
    menu: { order: 3, icon: "line-awesome/svg/file.svg" },
    title: "Logout",
};

const Logout: React.FC = () => {
    const navigate = useNavigate();

    useEffect(() => {
        const token = getCookie("access_token");
        if (token) {
            eraseCookie("access_token");
            setTimeout(() => {
                navigate("/Login");
            }, 2000);
        } else {
            setTimeout(() => {
                navigate("/Home");
            }, 2000);
        }
    }, [navigate]);

    return (
        <div>
            <Typography variant="h6" align="center">
                Logging out...
            </Typography>
        </div>
    );
};

export default Logout;
