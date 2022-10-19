import Link from "next/link";
import { useRouter } from "next/router";
import React from "react";

import { linksNavUser, linksNavBuss } from "./links";

const style = {
  title: `mx-4 text-sm uppercase active:font-semibold`,
  active: `bg-PurpleNavy text-lg text-white rounded-full font-semibold`,
  link: `flex items-center text-md justify-start my-1 p-2 w-full text-PurpleNavy hover:font-semibold`,
  close: `lg:duration-700 lg:ease-out lg:invisible lg:opacity-0 lg:transition-all`,
  open: `lg:duration-500 lg:ease-in lg:h-auto lg:opacity-100 lg:transition-all lg:w-auto`,
};

export default function SideItems() {
  const [links, setLinks] = React.useState([]);
  const userRol = localStorage.getItem("rolUser")

  const getLinksRol = () => {
    if (userRol == "USER") {
      setLinks(linksNavUser);
    } else {
      setLinks(linksNavBuss);
    }
  };

  React.useEffect(() => {
    getLinksRol();
  }, []);

  const { asPath } = useRouter();

  if (links.length > 0) 
  return (
    <ul className="md:pl-3">
      <li>
        {links.map((item) => (
          <Link href={item.link} key={item.title}>
            <a className={style.link}>
              <div
                className={`p-2 ${item.link === asPath ? style.active : ""}`}
              >
                <span>{item.icon}</span>
              </div>
              <span className={style.title}>{item.title}</span>
            </a>
          </Link>
        ))}
      </li>
    </ul>
  );
}
