/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    './src/**/*.{js,jsx,ts,tsx}',
    "./pages/**/*.{js,ts,jsx,tsx}",
  ],
  theme: {
    extend: {
      colors:{
       'MiddleYellow': '#FFE93E',
       'PurpleNavy':'#3d1365',
      }
    },
  },
  plugins: [],
}