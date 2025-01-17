import { ViewConfig } from "@vaadin/hilla-file-router/types.js";
import React from "react";

export const config: ViewConfig = {
    menu: { order: 3, icon: "line-awesome/svg/file.svg" },
    title: "My Cart",
};

const CartView: React.FC = () => {
    return (
        <div>
            <h1>Cart</h1>
        </div>
    );
};
export default CartView;
