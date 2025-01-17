import { createRoot } from "react-dom/client";
import { BrowserRouter } from "react-router-dom";

const root = createRoot(document.getElementById("outlet")!);
root.render(
    <BrowserRouter>
        <App />
    </BrowserRouter>,
);

import App from "Frontend/App";
