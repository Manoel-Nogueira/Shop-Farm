/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./index.html",
    "./src/**/*.{js,ts,jsx,tsx}",
  ],
  theme: {
    extend: {
      fontFamily: {
        lalezar: ["Lalezar", "Static"],
        potta: ["Potta One", "Static"],
        lilita: ["Lilita One", "Static"],
        poppins: ["Poppins", "Static"]
      },
      boxShadow: {
        "top": "0 -2px 6px  #475569"
      }
    },
  },
  plugins: [],
}

