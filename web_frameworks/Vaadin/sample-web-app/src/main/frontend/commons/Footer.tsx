import DescriptionIcon from "@mui/icons-material/Description";
import AuctionIcon from "@mui/icons-material/Gavel";
import HomeIcon from "@mui/icons-material/Home";
import InfoIcon from "@mui/icons-material/Info";
import LoginIcon from "@mui/icons-material/Login";
import SecurityIcon from "@mui/icons-material/Security";
import classNames from "classnames";
import React from "react";
import { Link, useLocation } from "react-router-dom";
import { ROUTING_PATH } from "Frontend/constants/endPoints";
import { FOOTER_LABEL } from "Frontend/constants/label";
import { useNavbar } from "Frontend/contexts/NavbarContext";

interface FooterLinkProps {
    href: string;
    icon: JSX.Element;
    text: string;
}

interface FooterSectionProps {
    title: string;
    links: FooterLinkProps[];
}

const FooterSection: React.FC<FooterSectionProps> = ({ title, links }) => {
    return (
        <div className="flex flex-col w-full md:w-1/6">
            <h3 className="mb-3 pl-8 p-2 rounded-3xl bg-gray-400 text-md font-bold text-[#121212]">
                {title}
            </h3>
            {links.map((link, index) => (
                <FooterButton key={index} {...link} />
            ))}
        </div>
    );
};

const FooterButton: React.FC<FooterLinkProps> = ({ href, icon, text }) => {
    const location = useLocation();
    const isActive = location.pathname === href;

    return (
        <Link
            to={href}
            className={classNames(
                "group mb-2 flex items-center font-bold justify-between rounded-full px-4 py-2 transition duration-300 ease-in-out",
                {
                    "text-white font-bold bg-[#11468F]": isActive,
                    "hover:bg-[#1967d3] hover:text-[#2A5069] text-[#2A5069]":
                        !isActive,
                },
            )}
        >
            <div>{icon}</div>
            <span className="ml-2 transition-all duration-300 ease-in-out">
                {text}
            </span>
        </Link>
    );
};

const Footer: React.FC = () => {
    const { isNavCollapsed } = useNavbar();

    const navLinks: FooterLinkProps[] = [
        {
            href: ROUTING_PATH.ROOT,
            icon: <HomeIcon />,
            text: FOOTER_LABEL.HOME,
        },
        {
            href: ROUTING_PATH.AUCTIONS,
            icon: <AuctionIcon />,
            text: FOOTER_LABEL.AUCTIONS,
        },
        {
            href: ROUTING_PATH.ABOUT,
            icon: <InfoIcon />,
            text: FOOTER_LABEL.ABOUT,
        },
    ];

    const policyLinks: FooterLinkProps[] = [
        {
            href: ROUTING_PATH.PRIVACY,
            icon: <SecurityIcon />,
            text: FOOTER_LABEL.PRIVACY_POLICY,
        },
        {
            href: ROUTING_PATH.TERMS,
            icon: <DescriptionIcon />,
            text: FOOTER_LABEL.TERMS_AND_CONDITIONS,
        },
    ];

    const accountLinks: FooterLinkProps[] = [
        {
            href: ROUTING_PATH.AUTH,
            icon: <LoginIcon />,
            text: FOOTER_LABEL.LOGIN,
        },
    ];

    return (
        <footer
            className={`bg-[#CACBCF] p-8 text-white shadow-lg
        ${isNavCollapsed ? "md:pl-24" : "md:pl-72"}
        pl-4
        transition-all duration-300`}
        >
            <div className="mx-auto flex justify-start gap-5 flex-col md:flex-row">
                <Link
                    to="/"
                    className="md:mb-0 flex items-center justify-center"
                >
                    <img
                        src="/favicon.svg"
                        alt="Koi Auction Logo"
                        className="w-24 max-w-full sm:w-20"
                    />
                </Link>

                <FooterSection title="Navigation" links={navLinks} />
                <FooterSection title="Policy" links={policyLinks} />
                <FooterSection title="Account" links={accountLinks} />
            </div>
            <div className="mt-5 text-center text-md font-bold text-gray-600">
                AuctionKoi.com is a division of SelectKoi.com
            </div>
        </footer>
    );
};

export default Footer;
