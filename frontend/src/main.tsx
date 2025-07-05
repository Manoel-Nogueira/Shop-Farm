import { StrictMode } from "react"
import { createRoot } from "react-dom/client"
import './index.css'
import { App } from "./App.tsx"
import { register } from "swiper/element/bundle";

register()
import "swiper/css";
import "swiper/css/navigation";
import "swiper/css/pagination";


createRoot(document.getElementById("root")!).render(
  <StrictMode>
    <App />
  </StrictMode>,
)
