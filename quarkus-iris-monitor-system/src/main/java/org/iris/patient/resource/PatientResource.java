package org.iris.patient.resource;

import org.iris.patient.dto.PatientInfoDTO;
import org.iris.patient.service.PatientService;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("/patient")
public class PatientResource {

    @Inject
    PatientService patientService;

    @GET
    @Path("/info")
    @Produces(MediaType.APPLICATION_JSON)
    public PatientInfoDTO searchPatientInfo(@QueryParam("key") String key) {
        return patientService.patientGetInfo(key);
    }
}
