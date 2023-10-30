// UserContext.js
import React, { createContext, useContext, useState, useEffect } from 'react';
import axios from "axios";
import UserService from "../services/UserService";

const UserContext = createContext();

export function useUser() {
    return useContext(UserContext);
}

export function UserProvider({ children }) {
    const [user, setUser] = useState(null);

    const fetchUserData = async () => {
        try {
            // Gọi API để lấy thông tin user
            const response = await UserService.getUser();
            if (response.ok) {
                const userData = await response.json();
                setUser(userData);
            } else {
                console.error('Lỗi khi lấy thông tin user');
            }
        } catch (error) {
            console.error('Lỗi khi gọi API:', error);
        }

        useEffect(() => {
            fetchUserData();
        }, []);

    }

    return (
        <UserContext.Provider value={user}>{children}</UserContext.Provider>
    );
}
