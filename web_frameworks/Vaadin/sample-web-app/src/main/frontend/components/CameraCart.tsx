import Camera from "Frontend/generated/com/lcaohoanq/samplewebapplication/models/Camera";
import React from "react";
import { Link } from "react-router-dom";

export interface CameraProps {
    camera: Camera;
}

export const CameraCard: React.FC<CameraProps> = ({ camera }) => {
    return (
        <div className="w-full h-full m-3">
            <div className="bg-white rounded-lg shadow-lg border border-gray-200 h-full flex flex-col transition-transform duration-300 hover:scale-105">
                <div className="flex-grow p-4 flex flex-col">
                    <div className="mb-4 h-48 flex items-center justify-center">
                        <img
                            src={camera.thumbnail}
                            alt={`${camera.brand} ${camera.model}`}
                            className="w-1/2 max-w-full max-h-full object-contain rounded-md"
                        />
                    </div>
                    <h2 className="text-xl font-semibold text-gray-800 mb-2">
                        {camera.brand} {camera.model}
                    </h2>
                    <p className="text-gray-600 mb-4 flex-grow overflow-hidden text-ellipsis">
                        {camera.description}
                    </p>
                    <p className="text-2xl font-bold text-green-600 mb-2">
                        ${camera.price}
                    </p>
                </div>
                <div className="p-4 bg-blue-200 border-t border-gray-200">
                    <Link
                        to={`/cameras/${camera.id}`}
                        className="block w-full text-center hover:bg-blue-700 text-white font-semibold py-2 px-4 rounded transition duration-300"
                    >
                        View Details
                    </Link>
                </div>
            </div>
        </div>
    );
};
