package fr.mathis_bruel.spacecube.bedwars.spigui.pagination;


import fr.mathis_bruel.spacecube.bedwars.spigui.SGMenu;
import fr.mathis_bruel.spacecube.bedwars.spigui.buttons.SGButton;

public interface SGPaginationButtonBuilder {

    SGButton buildPaginationButton(SGPaginationButtonType type, SGMenu inventory);

}
