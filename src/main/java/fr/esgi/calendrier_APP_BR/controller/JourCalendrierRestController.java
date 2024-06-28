//package fr.esgi.calendrier_APP_BR.controller;
//
//import fr.esgi.calendrier_APP_BR.business.JourCalendrier;
//import fr.esgi.calendrier_APP_BR.service.JourCalendrierService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@Api(tags = "JourCalendrier")
//@RestController
//@RequestMapping("/api/jour")
//public class JourCalendrierRestController {
//
//    @Autowired
//    private JourCalendrierService jourCalendrierService;
//
//    @ApiOperation(value = "Récupère la liste des jours du calendrier")
//    @GetMapping
//    public List<JourCalendrier> recupererJours() {
//        return jourCalendrierService.findAll();
//    }
//
//    @ApiOperation(value = "Récupère un jour du calendrier par son id")
//    @GetMapping("/{id}")
//    public JourCalendrier recupererJourParId(@PathVariable Long id) {
//        return jourCalendrierService.findById(id);
//    }
//
//    @ApiOperation(value = "Crée un jour du calendrier")
//    @PostMapping
//    public JourCalendrier creeJour(@RequestBody JourCalendrier jourCalendrier) {
//        return jourCalendrierService.save(jourCalendrier);
//    }
//
//    @ApiOperation(value = "Modifie un jour du calendrier par son id")
//    @PutMapping("/{id}")
//    public JourCalendrier updateJour(@PathVariable Long id, @RequestBody JourCalendrier jourDetails) {
//        JourCalendrier jour = jourCalendrierService.findById(id);
//        if (jour != null) {
//            jour.setDate(jourDetails.getDate());
//            return jourCalendrierService.save(jour);
//        }
//        return null;
//    }
//}
//
