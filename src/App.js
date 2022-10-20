import "./App.css";
import { ToolBar } from "./components/HomePage/Toolbar";
import { Route, Routes } from 'react-router-dom';
import { Index } from "./components/HomePage/Index";
function App() {
  return (
      <div className="App">
      <ToolBar />
      <Routes>
        <Route path="/" element={<Index/>} />
        <Route      path="/*"     element={  <h1>  Pagina no encontrada   </h1>
          }
        />
      </Routes>
    </div>

  );
}

export default App;
