import { BrowserRouter, Route, Router, Routes } from "react-router-dom"
import Pages from "./pages"

function App() {

  return (
// existe a <> </>
    <BrowserRouter>
      <Routes>
        {
          Pages.map((page, index) => (<Route key={index} path={page.path} element={page.component}/>))
        }
      </Routes>
    </BrowserRouter>
  
  );
}

export default App
