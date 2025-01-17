import React, { createContext, useContext, useState, useEffect } from "react";

interface NavbarContextType {
    isNavCollapsed: boolean;
    isMobileNavVisible: boolean;
    toggleNavCollapse: () => void;
    toggleMobileNav: () => void;
}

const NavbarContext = createContext<NavbarContextType | undefined>(undefined);

export function NavbarProvider({ children }: { children: React.ReactNode }) {
    const [isNavCollapsed, setIsNavCollapsed] = useState(true);
    const [isMobileNavVisible, setIsMobileNavVisible] = useState(
        window.innerWidth >= 768,
    );

    useEffect(() => {
        const handleResize = () => {
            setIsNavCollapsed(true);
            setIsMobileNavVisible(window.innerWidth < 768);
        };

        window.addEventListener("resize", handleResize);
        return () => window.removeEventListener("resize", handleResize);
    }, []);

    const toggleNavCollapse = () => {
        setIsNavCollapsed((prev) => !prev);
    };

    const toggleMobileNav = () => {
        setIsMobileNavVisible((prev) => !prev);
    };

    return (
        <NavbarContext.Provider
            value={{
                isNavCollapsed,
                isMobileNavVisible,
                toggleNavCollapse,
                toggleMobileNav,
            }}
        >
            {children}
        </NavbarContext.Provider>
    );
}

export function useNavbar() {
    const context = useContext(NavbarContext);
    if (context === undefined) {
        throw new Error("useNavbar must be used within a NavbarProvider");
    }
    return context;
}
