import React from "react";
import { useRouter } from "next/router";
import Cookies from "js-cookie";

export default function LogOut() {
  const router = useRouter();

  React.useEffect(() => {
    try {
      Cookies.remove("token");
      Cookies.remove("user");
      router.push("/landing");
    } catch (error) {
      alert("Cannot Logout");
      console.log(error);
      return;
    }
  }, []);

  return <></>;
}
