import {useEffect, useState} from "react";

function useLocalState(defaultValue, key) {
    const [value, setValue] = useState(() => {
        const storageValue = localStorage.getItem(key);
        return storageValue !== null ?
            JSON.parse(storageValue) : defaultValue;
    });

    useEffect(() => {
        localStorage.setItem(key, JSON.stringify(value));
    }, [key, value]);

    return [value, key];
}
export { useLocalState };