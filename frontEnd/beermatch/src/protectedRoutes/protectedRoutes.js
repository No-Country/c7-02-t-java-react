import Cookies from "js-cookie";
import { useRouter } from "next/router";
import React from "react";
import Loading from "./loading";

export default function ProtectedRoute({ children }) {
  const router = useRouter();

  // if (token) return <Loading/>;
  React.useEffect(() => {
    if (Cookies.get("token")) {
      router.push("/dashboard/main");
    } else {
      router.push("/");
    }
  }, []);

  // if (typeof window !== "undefined") if (!token) router.push("/");

  // return <>{children}</>;
}
