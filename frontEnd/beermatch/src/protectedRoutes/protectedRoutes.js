import { useRouter } from "next/router";
import React from "react";
import Loading from "./loading";

export default function ProtectedRoute({ children }) {
  const router = useRouter();

  // if (token) return <Loading/>;
  React.useEffect(() => {
    if (localStorage.getItem("token")) {
      return <>{children}</>;
    } else {
      router.push("/");
    }
  }, []);

  // if (typeof window !== "undefined") if (!token) router.push("/");

  // return <>{children}</>;
}
