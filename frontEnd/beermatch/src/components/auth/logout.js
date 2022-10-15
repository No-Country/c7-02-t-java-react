import React from "react";
import { useRouter } from "next/router";

export default function LogOut() {
  const router = useRouter();

  React.useEffect(() => {
    try {
      localStorage.removeItem("token");
      localStorage.removeItem("user");
      router.push("/landing");
    } catch (error) {
      alert("Cannot Logout");
      console.log(error);
      return;
    }
  }, []);

  return <></>;
}
