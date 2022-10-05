import { useState } from "react";
import Link from "next/link";
import { FaUserCircle } from "react-icons/fa";
import { IoNotificationsOutline } from "react-icons/io5";

const style = {
  icon: "m-2 text-PurpleNavy w-6 h-6 ",
  link:"text-xl font-thin my-4 text-PurpleNavy"
};

function MobileNav({ open, setOpen }) {
  return (
    <div
      className={`absolute top-0 left-0 h-screen w-screen lg:hidden bg-violet-100 transform ${
        open ? "-translate-x-0" : "-translate-x-full"
      } transition-transform duration-300 ease-in-out filter drop-shadow-md `}
    >
      <div className="flex items-center justify-center filter drop-shadow-md shadow-PurpleNavy bg-violet-100 h-20">
        {" "}
        {/*logo container*/}
        <a className="text-xl font-semibold" href="/">
          <img
            className="m-auto justify-center h-auto flex"
            src="/logo.png"
            alt=""
            width={100}
          />
        </a>
      </div>
      <div className="flex flex-col ml-4" onClick={() =>
            setTimeout(() => {
              setOpen(!open);
            }, 100)
          }>
        <a
          className={style.link}
          href="/dashboard/main"
          
        >
          Inicio
        </a>
        <a
          className={style.link}
          href="/dashboard/posts"

        >
          Mis comentarios
        </a>
        <a
          className={style.link}
          href="/dashboard/business"
        >
          Donde ir?
        </a>
        <a
          className={style.link}
        >
          Cerrar sesion
        </a>

      </div>
    </div>
  );
}

export default function Navbar() {
  const [open, setOpen] = useState(false);
  return (
    <div>
      <div className="sticky top-0">
        <nav className="flex filter drop-shadow-md shadow-PurpleNavy bg-violet-100 px-4 py-4 h-20 items-center">
          <MobileNav open={open} setOpen={setOpen} />

          <div className="w-3/12 flex items-center">
            <a className="text-2xl font-semibold" href="/">
              <img
                className="m-auto justify-center h-auto flex md:hidden"
                src="/logo.png"
                alt=""
                width={100}
              />
            </a>
          </div>
          
          <div className="w-9/12 flex justify-end items-center">
          <div className="flex mx-4">
              <Link href="/contact">
                <IoNotificationsOutline className={style.icon} />
              </Link>
              <Link href="/dashboard/profile">
                <FaUserCircle className={style.icon} />
              </Link>
            </div>

            <div
              className="z-50 flex relative w-8 h-8 flex-col justify-between items-center md:hidden"
              onClick={() => {
                setOpen(!open);
              }}
            >
              {/* hamburger button */}
              <span
                className={`h-1 w-full bg-PurpleNavy rounded-lg transform transition duration-300 ease-in-out ${
                  open ? "rotate-45 translate-y-3.5" : ""
                }`}
              />
              <span
                className={`h-1 w-full bg-PurpleNavy  rounded-lg transition-all duration-300 ease-in-out ${
                  open ? "w-0" : "w-full"
                }`}
              />
              <span
                className={`h-1 w-full bg-PurpleNavy  rounded-lg transform transition duration-300 ease-in-out ${
                  open ? "-rotate-45 -translate-y-3.5" : ""
                }`}
              />
            </div>
          </div>
        </nav>
      </div>
    </div>
  );
}
