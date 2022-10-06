import React from "react";
import Navbar from "./navbar/navbar";
import Sidebar from "./navbar/sideBar";

const style = {
  container: `bg-PurpleNavy h-screen overflow-hidden relative`,
  mainContainer: `flex flex-col h-screen pl-0 w-full lg:pl-60 lg:space-y-4`,
  main: `h-screen overflow-auto pb-36 pt-4 px-2 md:pb-8 md:pt-4 lg:pt-0 lg:px-4 `,
};

function Layout({ children }) {
  return (
    <div className={style.container}>
      <div className="flex items-start">
        <Sidebar />
        <div className={style.mainContainer}>
        <Navbar />
          <main className={style.main}>{children}</main>
        </div>
      </div>
    </div>
  );
}

export default Layout;
