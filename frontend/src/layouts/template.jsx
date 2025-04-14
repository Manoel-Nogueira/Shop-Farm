import { ThemeProvider } from "flowbite-react";
import Navbar from "@Components/navbar";
import Theme from "../theme";

export default function Template({ children }) {
    return(
        <>

            <ThemeProvider >
                <Navbar />

                {children}
            </ThemeProvider>

        </>
    );
}