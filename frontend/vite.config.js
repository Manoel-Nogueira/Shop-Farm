import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'
import tailwindcss from '@tailwindcss/vite'
import flowbiteReact from "flowbite-react/plugin/vite";
import path from "path";

// https://vite.dev/config/
export default defineConfig({
  plugins: [react(), tailwindcss(), flowbiteReact()],
  resolve: {
    alias: {
      "@": path.resolve("./src"),
      "@Components": path.resolve("./src/components"),
      "@Controllers": path.resolve("./src/controllers"),
      "@Layouts": path.resolve("./src/layouts"),
      "@Pages": path.resolve("./src/pages"),
      "@Theme": path.resolve("./src/theme"),
    },
  },
});