// Import the functions you need from the SDKs you need
import { initializeApp } from "firebase/app";
import { getAnalytics } from "firebase/analytics";
import { getMessaging, getToken } from "firebase/messaging";
// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
// For Firebase JS SDK v7.20.0 and later, measurementId is optional
const firebaseConfig = {
    apiKey: "AIzaSyB8txnXqRcjedv9PQLxNxLabk4T8TYDFQY",
    authDomain: "student-48d79.firebaseapp.com",
    projectId: "student-48d79",
    storageBucket: "student-48d79.appspot.com",
    messagingSenderId: "572626028708",
    appId: "1:572626028708:web:b756c34189076ba1147e31",
    measurementId: "G-3X2FLY4F6P"
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);
const analytics = getAnalytics(app);
const messaging = getMessaging();

getToken(messaging, {vapidKey: "BN8pYSR80UE168DK_6WGHPQwkZS8eh3w-wwxWJoD8w-vDP0IURobeMdZudgPjTpK7HsOINjTxoMPoXlWrri7uVY"});

