import { IoHome, IoBeer} from "react-icons/io5";
import { MdOutlineComment, MdOutlinePlace } from "react-icons/md";
import { VscSignOut } from "react-icons/vsc";
import { FaUserCircle } from "react-icons/fa";
import { BsMegaphone } from "react-icons/bs";


// const style = {
//   icon: "m-2 text-PurpleNavy w-6 h-6 ",
//   link: "text-xl font-thin my-4 ml-2 text-PurpleNavy hover:font-semibold ",
// };

  export const linksNavUser = [
    {
      title: "Inicio",
      icon: <IoHome />,
      link: "/dashboard/main",
    },
    {
      title: "Mis Comentarios",
      icon: <MdOutlineComment />,
      link: "/dashboard/posts",
    },
    {
      title: "donde ir?",
      icon: <IoBeer />,
      link: "/dashboard/business",
    },
    {
      title: "Mi perfil",
      icon: <FaUserCircle />,
      link: "/dashboard/profile",
    },
    {
      title: "Cerrar sesion",
      icon: <VscSignOut />,
      link: "/logout",
    },
  ];

  export const linksNavBuss = [
    {
      title: "Inicio",
      icon: <IoHome />,
      link: "/dashboard/main",
    },
    // {
    //   title: "Mis Comentarios",
    //   icon: <MdOutlineComment />,
    //   link: "/dashboard/posts",
    // },
    {
      title: "Negocios",
      icon: <IoBeer />,
      link: "/dashboard/business",
    },
    {
      title: "Mis negocios",
      icon: <MdOutlinePlace/>,
      link: "/dashboard/business/myBusiness",
    },
    {
      title: "Mis anuncios",
      icon: <BsMegaphone/>,
      link: "/dashboard/business/myNews",
    },
    {
      title: "Mi perfil",
      icon: <FaUserCircle />,
      link: "/dashboard/profile",
    },
    {
      title: "Cerrar sesion",
      icon: <VscSignOut />,
      link: "/logout",
    },
  ];


// // export default {linksNavUser, linksNavBuss};
// export default linksNavBuss;
