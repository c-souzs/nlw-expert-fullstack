import { createBrowserRouter } from "react-router-dom";
import Home from "@/pages/Home";
import Certification from "@/pages/Certification";
import CertificationResult from "@/pages/CertificationResult";
import Dashboard from "@/pages/Dashboard";

const routes = createBrowserRouter(
  [
    {
      path: "/",
      element: <Home />
    },
    {
      path: ":id/certification",
      element: <Certification />,
      children: [
        {
          path: ":id/certification/result",
          element: <CertificationResult />
        }
      ]
    },
    {
      path: "/dashboard",
      element: <Dashboard />
    }
  ],
  {
    basename: "/app"
  }
)

export default routes;