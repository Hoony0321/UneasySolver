import { atom } from "recoil";
import { ATOM_AUTHENTICATION } from "../../utils/constants/state.constants";

export interface IAuthenticationAtom {
	token: string;
	id: number;
	email: string;
	nickname: string;
}

const authenticationAtom = atom({
	key: `${ATOM_AUTHENTICATION}`,
	default: { token: "", id: -1, email: "", nickname: "" },
});

export { authenticationAtom };
